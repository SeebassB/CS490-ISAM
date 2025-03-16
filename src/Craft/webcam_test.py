import cv2

# Open webcam (0 is the default camera, change if using an external one)
cap = cv2.VideoCapture(0)

if not cap.isOpened():
    print("Error: Could not open webcam.")
else:
    ret, frame = cap.read()  # Capture a single frame
    if ret:
        cv2.imshow("Webcam Test", frame)  # Display the captured image
        cv2.imwrite("test_capture.jpg", frame)  # Save the image
        cv2.waitKey(0)  # Wait until a key is pressed
        cv2.destroyAllWindows()  # Close the window
    else:
        print("Error: Could not capture image.")

cap.release()  # Release the webcam
