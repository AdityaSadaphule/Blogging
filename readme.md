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
    <h3>SignIn API :- currently cors is disabled for Frontend testing so I'm not exposing the link '/signIn'</h3>
    
    <p>
    </p>
  </li>
  
</ol>
