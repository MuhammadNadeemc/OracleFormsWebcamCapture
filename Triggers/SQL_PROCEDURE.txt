CREATE OR REPLACE PROCEDURE save_blob_to_file(
   p_id IN NUMBER,                   -- ID of the record
   p_blob_column IN VARCHAR2,        -- The BLOB column
   p_table_name IN VARCHAR2,         -- The table name
   p_file_name OUT VARCHAR2          -- The file name to be saved (relative)
) IS
   v_blob BLOB;
   v_name VARCHAR2(100);             -- Variable to hold the 'name' value
   v_buffer RAW(2000);               -- Smaller buffer size for chunked read
   v_blob_length INTEGER;
   v_position INTEGER := 1;
   v_bytes_to_read INTEGER;          -- Variable to store how many bytes are read in each iteration
   v_chunk_size CONSTANT INTEGER := 2000; -- Chunk size set to 2000 bytes
   v_file_handle UTL_FILE.FILE_TYPE;
   v_timestamp VARCHAR2(30);         -- Variable to store the timestamp
BEGIN
   -- Fetch the BLOB and the 'name' column from the specified table
   EXECUTE IMMEDIATE 'SELECT ' || p_blob_column || ', name FROM ' || p_table_name || ' WHERE id = :id'
      INTO v_blob, v_name
      USING p_id;

   -- Get the current timestamp in the format YYYY-MM-DD_HH24:MI:SS
   v_timestamp := TO_CHAR(SYSDATE, 'YYYY-MM-DD_HH24:MI:SS');

   -- Create the file name based on ID and timestamp
   p_file_name := p_id || '_' || v_timestamp || '.jpg';

   -- Replace colons in the file name with underscores (since colons are not allowed in filenames)
   p_file_name := REPLACE(p_file_name, ':', '_');

   -- Get the length of the BLOB
   v_blob_length := DBMS_LOB.getlength(v_blob);

   -- Open the file for writing (use the Oracle Directory Object and the file name)
   v_file_handle := UTL_FILE.FOPEN('ARCHIVE_DIR', p_file_name, 'wb');

   -- Write the BLOB data to the file in chunks of 2000 bytes
   WHILE v_position <= v_blob_length LOOP
      -- Calculate the number of bytes to read in this iteration
      v_bytes_to_read := LEAST(v_chunk_size, v_blob_length - v_position + 1);

      -- Read the BLOB chunk
      DBMS_LOB.READ(v_blob, v_bytes_to_read, v_position, v_buffer);

      -- Write the buffer to the file
      UTL_FILE.PUT_RAW(v_file_handle, v_buffer, TRUE);

      -- Move the position forward
      v_position := v_position + v_bytes_to_read;

      -- Log progress
      DBMS_OUTPUT.PUT_LINE('Chunk written, position: ' || v_position);
   END LOOP;

   -- Close the file
   UTL_FILE.FCLOSE(v_file_handle);

   -- Log success
   DBMS_OUTPUT.PUT_LINE('Image saved successfully as ' || p_file_name);

EXCEPTION
   WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
      IF UTL_FILE.IS_OPEN(v_file_handle) THEN
         UTL_FILE.FCLOSE(v_file_handle);
      END IF;
      RAISE;
END;
/
