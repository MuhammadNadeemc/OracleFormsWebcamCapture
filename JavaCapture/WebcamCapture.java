import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class WebcamCapture {

    static {
        // Load the OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        // Define the path where the image will be saved
        String outputDir = "C:/Temp";
        String imagePath = outputDir + "/webcam_capture.jpg";

        // Ensure the output directory exists
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Initialize the webcam (0 is the default webcam)
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Error: Could not access the webcam.");
            return;
        }

        // Capture a single frame from the webcam
        Mat frame = new Mat();
        if (camera.read(frame)) {
            // Save the image to the specified path
            Imgcodecs.imwrite(imagePath, frame);
            System.out.println("Image saved at: " + imagePath);
        } else {
            System.out.println("Error: Could not capture an image.");
        }

        // Release the camera
        camera.release();
    }
}
