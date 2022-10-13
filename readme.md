<h1>Blogging System Backend</h1>

<h2>These are the API which are currently live and there features</h3>
<ol>
  <li>
    <h3>Login API :- currently cors is disabled for Frontend testing so I'm not exposing the link '/login'</h3>
    <p>
      We will have to post an JSON body which will contain the username and password. This will generate a JWT which will be passed in all the API request to all the 
      other APIs. The JWT should be passed in the header as authentication.
     </p>
      <H4><b>Return Status Codes<b></H4>
      <ul>
        <li>
        <p>
          <emp>Status Code 401 (Unauthorized) :- </emp>
          When the inputs provided are invalid and the credentials are not present in the database then we will get a Status Code 401 along with a message that says status credentials are invalid.
         </p>
         </li>
         <li>
        <p>
          <emp>Status Code 500 (Internal Server Issue) :- </emp>
          If there is anything wrong with the server then instead of not providing any respond or not catching the exception we would be returning a Status Code 500 which will give a message Something went wrong please try again.
         </p>
         </li>
         <li>
        <p>
          <emp>Status Code 200 (OK) :- </emp>
          When the user inputs the correct credentials and is authenticated as a valid user then the user will get a status code 200 along with the JWT authentication key.
         </p>
         </li>
      </ul>
  </li>
  <li>
    <h3>Register API :- currently cors is disabled for Frontend testing so I'm not exposing the link '/register'</h3>
    <p>
    Register API will accept a user json body which will have the fileds:-
    <ul>
    <li>username :- the username cannot contain any special character except '@' and '_' same goes for other fields</li>
    <li>password :- password follows the standard password requirements.</li>
    <li>dateOfBirth :- If making request from Node then date type variable or Front-end then a simple form date picker might do the work. For Postman format should be yyyy-mm-ddT00:00.000+00:00 . From other sources also the call should be made for GMT timezone not yet restricted but might do in future.</li>
    <li>email :- Emails are currently not being treated as valid emails they can be anything, in future implementation I might add email validation.</li>
    <li>phoneNumber :- phoneNumber is also taken as a string of length 10. Not taking region code yet.</li>
    </ul>
    </p>
  </li>
  
  <li>
    <h3>Create Blog API :- currently cors is disabled for Frontend testing so I'm not exposing the link '/blog/createBlog'</h3>
    <p>
      It is an JWT authenticated API so the User will have to send the JWT token in the headers as Authorization with the Initials as Bearer. The API needs a Response Body with the following Fileds 
      <ul>
      <li>username :- The username which we created during the registration.</li>
      <li>content :- It is the content which want to put in our blog.</li>
      <li>subject :- It would be the subject of the blog.</li>
      </ul>
      Once the requested Body is provied along with the JWT, it will give us a blog ID in return. The same user cannot create a blog for the next 10 minutes and even changing the username won't help. 
    </p>
  </li>
  
  <li>
    <h3>All Blogs API :- currently cors is disabled for Frontend testing so I'm not exposing the link '/blog/all'</h3>
    <p>
      It is a unauthenticated API and the user can get the response even if they are not logged in or a registered user. The implementation is like this because let's say if we share a link for our blog it should reach to everyone and the person should be able to see it. This API doesn't require any query string.
    </p>
  </li>
  
  <li>
    <h3>Blog By Id API :- currently cors is disabled for Frontend testing so I'm not exposing the link '/blog?blogId=""'</h3>
    <p>
      It is also a unauthenticated API and the user has to send a querySting which contains the blog ID.
    </p>
  </li>
  
</ol>
