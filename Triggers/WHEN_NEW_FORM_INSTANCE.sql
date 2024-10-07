BEGIN
   execute_query;
   
   -- Set the Java executable and library path
   :global.java_exec := 'C:\Program Files\Java\jdk1.8.0_281\bin\javaw.exe';
   :global.java_lib_path := 'C:\FormsScripts\JavaCapture';
   
   -- Set the static image file path (no need for a text file)
   :global.temp_dir := 'C:\Temp';
   :global.image_file := 'webcam_capture.jpg'; -- Direct reference to the image file

   -- Set the block and image item dynamically for flexibility
   :global.block_name := 'BC_IRRADIATION_DTL';
   :global.image_item := 'PL_IMAGE';

  -- Ensure this matches the column name in the table
   :global.image_column := 'pl_image';  
END;
