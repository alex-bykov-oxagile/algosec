# algosec
Code writing task:
Find the first X point where maximum number of the segments are intersecting and indicate how many segments are intersecting in that point

This is Web Application based on REST API.

 How to run App?

 1) Check out Git https://github.com/alex-bykov-oxagile/algosec
 2) Execute gradle bootRun task (or Import algosec project in IDE (run XPointApplication))
 3) Open this url in your local browser http://localhost:8080/swagger-ui.html
 4) Click on x-point-controller link
 5) Click on /api/find/xpoint link
 6) Put the JSON below on Value area section:
 [ { "startPoint": -12, "endPoint": 5 }, { "startPoint": 2, "endPoint": 33 }, { "startPoint": 6, "endPoint": 15 }, { "startPoint": 3, "endPoint": 5 } ]
 7) Click "Try it out!" button
 8) Take a look at "Response Body" area: 3  <- result


Regards,
Alex