import java.io.File;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class WebcamCapture {

    static {
        // Load the OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        // Define the output directory and image file path
        String outputDir = "C:/Temp";
        String imagePath = outputDir + "/webcam_capture.jpg";

        // Ensure the output directory exists
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Initialize the webcam (0 is the default webcam)
        VideoCapture camera = new VideoCapture(0, Videoio.CAP_DSHOW); // Use DirectShow to reduce warnings
        if (!camera.isOpened()) {
            System.out.println("Error: Could not access the webcam.");
            return;
        }

        // Set the camera resolution to the maximum supported
        camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 3840);  // Set to a very high resolution, e.g., 4K
        camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 2160);

        // Adjust camera properties
        camera.set(Videoio.CAP_PROP_AUTOFOCUS, 0); // Turn off auto-focus (0 = off, 1 = on)
        camera.set(Videoio.CAP_PROP_FOCUS, 10); // Set focus manually, adjust value as needed
        camera.set(Videoio.CAP_PROP_AUTO_EXPOSURE, 0.25); // 0.25 = Manual mode for some webcams
        camera.set(Videoio.CAP_PROP_BRIGHTNESS, 150); // Adjust brightness, values vary by camera

        // Allow the camera some time to adjust settings
        try {
            Thread.sleep(2000); // Delay for 2 seconds
        } catch (InterruptedException e) {
            System.out.println("Error: Interrupted during delay.");
            Thread.currentThread().interrupt();
        }

        // Capture a few frames to let the camera stabilize
        Mat frame = new Mat();
        for (int i = 0; i < 5; i++) {
            camera.read(frame);
        }

        // Set JPEG quality to maximum (100) for highest quality output
        MatOfInt params = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, 100);

        // Save the final image
        boolean result = Imgcodecs.imwrite(imagePath, frame, params);
        if (result) {
            System.out.println("Image saved at: " + imagePath + " with maximum quality.");
        } else {
            System.out.println("Error: Could not save the image.");
        }

        // Release the camera
        camera.release();
    }
}
