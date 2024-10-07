DECLARE
   v_file_name VARCHAR2(256);
BEGIN
   -- Call the stored procedure to save the BLOB to the file
   save_blob_to_file(
      p_id => :bc_irradiation_dtl.id,              -- ID from the form
      p_blob_column => :global.image_column, -- BLOB column (dynamic)
      p_table_name => :global.block_name,    -- Table name (dynamic)
      p_file_name => v_file_name             -- Output file name
   );

EXCEPTION
   WHEN OTHERS THEN
      MESSAGE('Error: ' || SQLERRM);
      PAUSE;
END;
