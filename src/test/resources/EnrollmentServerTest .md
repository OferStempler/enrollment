<div align="center" style="color:grey">
    <img src="https://i.imgur.com/Nr8AyzO.png" alt="logo" width="130"/><br />
    ---This document is proprietary and confidential---<br />
    No part of this document may be disclosed in any manner to a third party without the prior written consent of Duda
</div>

# Duda Test

You are asked to create school enrollment system according to the following requirements,
using a standalone web server. In order to implement that we recommend to use Java + [Spring Boot](https://spring.io/guides/gs/rest-service/). However, you can choose any technology stack you are comfortable with.

You need to implement the following rest endpoints:

1. Create a pupil. - Method:POST - Endpoint: /pupil - Payload:
   `javascript { Lat: Double, Lon: Double, Grades : [ { couseName : String, grade : Integer } ] } ` - Return value: id(of type Long) of the created pupil.
   <br/>
2. Create a school. - Method:POST - Endpoint: /school - Payload:
   `javascript { Lat: Double, Lon: Double, minimumGpa: Integer, maxNumberOfPupils: Integer } `
   Return value: id(of type Long) of the created school.
   <br />

3. Set friendship between two pupils.
   This relation is symmetric,<br />
   i.e. pupil a is friend of pupil b if and only if pupil b is friend of pupil a. - Endpoint: /setFriendShip/{firstPupilId}/{secondPupilId} - Method:POST - No return value.
   <br />

4. Enroll a pupil to a school,
   Pupil will be enrolled to a school that maximizes the following formula:
   NUMBER OF FRIENDS IN THE SCHOOL \* 1 / DISTANCE FROM SCHOOL(KM)
   Where the distance can be calculated using (Haversine formula)[https://en.wikipedia.org/wiki/Haversine_formula].
   Keep in mind that in order to enroll a pupil to a specific school,
   the pupil GPA should be greater than schools minimum GPA and the number of enrolled pupils has to be less than the schools max number of pupils. - Endpoint: /enroll/{pupilId} - Method: POST - No return value.
   <br />

#### Bonus(Optional)

Return a static page that renders a map,
showing all the schools and the pupils.
It can be implemented using either client or server side rendering. You may expose any other rest API to implement this task.
Further explaination about rendering maps can be found in the [appendix](#appendix) - Endpoint: / - Method: GET

### Submission guidelines

- You should submit: 1. The source code of your project 2. Java: <br>
  a. A single jar named duda.jar, which should contain a single main method. <br>
  b. We will run it using the following command: java -jar duda.jar. <br>
  c. The project should run on Java 8 and above(mention java version on submission). <br> 3. Not Java - Provide prerequisite and guidelines to run the project locally.
  <br />

## Remarks

- In order to store the data you can use an in memory DB (e.g: H2)
- You may use well known open source libraries in order to complete this task.
- You shouldn't handle edge cases except from these were mentioned in this file.
  <br />
  <br />

## Good Luck!

<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />

## Appendix

The map provider you should use for this test is MapBox.<br />
You can find an explanation on how to add a map to your index page [here](https://www.mapbox.com/mapbox-gl-js/example/simple-map/).<br />
Explaination about how to add the pupils and schools:https://docs.mapbox.com/mapbox-gl-js/example/geojson-markers/
(Note that you can use circle/point for pupil icon and school as school icon)

To use mapbox services you need this access token: <br />
`pk.eyJ1IjoiZGFubnliMTIzIiwiYSI6ImNqMGljZ256dzAwMDAycXBkdWxwbDgzeXYifQ.Ck5P-0NKPVKAZ6SH98gxxw`
