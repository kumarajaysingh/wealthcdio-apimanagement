# wealthcdio-apimanagement

Requirements: 
The system must manage traffic light state transitions (Red, Yellow, Green) across multiple directions.
It should accept external commands to modify light sequences, as well as to pause and resume operations.
The system must enforce safety rules by ensuring that conflicting directions are never set to Green simultaneously.
An API must be provided to retrieve the current light states and their timing history.
The architecture should support concurrent operations and be designed for future scalability to handle multiple intersections.

API Documentation:
The system exposes RESTful APIs to manage and monitor traffic light operations.

1. Create Intersection / Initialize System
   Description: Initializes a new traffic light configuration or intersection for bangalore location.

Method: POST
Request URL: http://localhost:8080/api/intersections/<bangalore>

Response Body:
{
"id": "bangalore",
"lights": {
"NORTH": {
"direction": "NORTH",
"state": "RED"
},
"SOUTH": {
"direction": "SOUTH",
"state": "RED"
},
"EAST": {
"direction": "EAST",
"state": "RED"
},
"WEST": {
"direction": "WEST",
"state": "RED"
}
},
"history": [],
"lock": {
"holdCount": 0,
"locked": false,
"queueLength": 0,
"heldByCurrentThread": false,
"fair": false
},
"paused": false
}


2. Update Light Sequence / Control Operation
   Description: Manage state changes of lights (red, yellow, green) for multiple directions.
Method: PUT
URL: http://localhost:8080/api/intersections/<bangalore>/lights/<EAST>?state=<GREEN>

3. Get Current State and Timing History
   Description: Retrieves the current traffic light states.
Method: GET
URL: http://localhost:8080/api/intersections/<bangalore>
Response:
   {
   "id": "bangalore",
   "lights": {
   "NORTH": {
   "direction": "NORTH",
   "state": "RED"
   },
   "SOUTH": {
   "direction": "SOUTH",
   "state": "RED"
   },
   "EAST": {
   "direction": "EAST",
   "state": "GREEN"
   },
   "WEST": {
   "direction": "WEST",
   "state": "RED"
   }
   },
   "history": [
   {
   "direction": "EAST",
   "state": "GREEN",
   "timestamp": "2026-03-03T14:27:43.607531600Z"
   }
   ],
   "lock": {
   "holdCount": 0,
   "locked": false,
   "queueLength": 0,
   "heldByCurrentThread": false,
   "fair": false
   },
   "paused": true
   }


4. Get current state and timing history.
   Description: Retrieves the current traffic light states along with timing history details. 

Method: GET
URL: http://localhost:8080/api/intersections/<bangalore>/history

Response:
[
{
"direction": "EAST",
"state": "GREEN",
"timestamp": "2026-03-03T14:27:43.607531600Z"
}
]

5.  Change light sequences as pause.
   Description: Retrieves the current traffic light states along with timing history details.

Method: POST
URL: hhttp://localhost:8080/api/intersections/bangalore/pause

Response:
[
{
"direction": "EAST",
"state": "GREEN",
"timestamp": "2026-03-03T14:27:43.607531600Z"
}
]

6. Change light sequences as resume.
   Description: Retrieves the current traffic light states along with timing history details.

Method: POST
URL: http://localhost:8080/api/intersections/bangalore/resume

Response:
[
{
"direction": "EAST",
"state": "GREEN",
"timestamp": "2026-03-03T14:27:43.607531600Z"
}
]

