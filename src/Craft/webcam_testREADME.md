Expected behavior:

A window should pop up showing your webcam feed. and it will take one picture and save it
as test_capture.jpg within the same folder


If it doesnt open the camer, we may need to try indexing 1 instead of 0 at
cap = cv2.VideoCapture(1);


Virtual Enviro setup:
if Open cv not installed, you can  do this in VS terminal
pip install opencv-python
