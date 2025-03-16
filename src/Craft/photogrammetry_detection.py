import cv2
import numpy as np

# Open the webcam
cap = cv2.VideoCapture(0)

if not cap.isOpened():
    print("Error: Could not open webcam.")
else:
    # Capture a single frame
    ret, frame = cap.read()
    if ret:
        cv2.imwrite("feasibility_test.jpg", frame)  # Save image
        print("Image captured and saved as feasibility_test.jpg")

        # Convert to grayscale
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

        # Apply ORB feature detection
        orb = cv2.ORB_create()
        keypoints, descriptors = orb.detectAndCompute(gray, None)
        image_with_keypoints = cv2.drawKeypoints(frame, keypoints, None, color=(0, 255, 0))

        # Show results
        cv2.imshow("Original Image", frame)
        cv2.imshow("Feature Detection", image_with_keypoints)
        cv2.waitKey(0)
        cv2.destroyAllWindows()

        # Count number of keypoints detected
        print(f"Number of keypoints detected: {len(keypoints)}")

    else:
        print("Error: Could not capture image.")

cap.release()  # Release the webcam
