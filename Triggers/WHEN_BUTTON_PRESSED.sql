DECLARE
   v_file_path VARCHAR2(1000);
BEGIN
   -- Execute the Java program using global variables for Java paths and library paths
   HOST(:global.java_exec || ' -Djava.library.path="' || :global.java_lib_path || '" -cp "' || :global.java_lib_path || '\opencv-411.jar;' || :global.java_lib_path || '" WebcamCapture');

   -- Construct the full file path for the image
   v_file_path := :global.temp_dir || '\' || :global.image_file;
   
   -- Load the image into the dynamically named block and item
   IF v_file_path IS NOT NULL THEN
      -- Use the constructed file path to load the image
      READ_IMAGE_FILE(v_file_path, 'JPEG', :global.block_name || '.' || :global.image_item);
      
      -- Log successful loading
      MESSAGE('Image loaded successfully, please save to push changes....');
   ELSE
      MESSAGE('No image selected.');
   END IF;
END;
