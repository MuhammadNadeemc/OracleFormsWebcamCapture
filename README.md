# Oracle Forms Webcam Integration

This repository provides all the necessary files and documentation for integrating webcam functionality into Oracle Forms using Java and OpenCV.

## Repository Structure

The repository contains the following files and directories:

- **Oracle Forms Webcam Integration Documentation.docx**: A detailed document explaining the steps for integrating webcam functionality into Oracle Forms, the procedures involved, and necessary configurations.
- **README.md**: This file, providing an overview of the repository and usage instructions.

### Directories

- **JavaCapture**:
  - `opencv-411.jar`: The OpenCV library required for image capture.
  - `opencv_java411.dll`: The necessary DLL for OpenCV integration on Windows.
  - `WebcamCapture.class`: Compiled Java class for webcam capture.
  - `WebcamCapture.java`: Source code of the webcam capture functionality.
  
  > **Note**: You **can drag and move** the **JavaCapture** folder into the desired location on your system to ensure the integration works. Refer to the documentation for details on the setup. While optional, all dependencies are provided here for convenience.

- **TestForm**:
  - `ViewTestForm.fmb`: A test Oracle Form that demonstrates how the integration works. You can open this in Oracle Forms Builder to see how the form is formatted.

- **Triggers**:
  - `POST_DATABASE_COMMIT.txt`: Contains the trigger code for the `POST-DATABASE-COMMIT` event.
  - `SQL_PROCEDURE.txt`: Example SQL procedure used in the integration. (Needs editinng to fit respective table)
  - `WHEN_BUTTON_PRESSED.txt`: Trigger code for the `WHEN-BUTTON-PRESSED` event.
  - `WHEN_NEW_FORM_INSTANCE.txt`: Trigger code for the `WHEN-NEW-FORM-INSTANCE` event.
