import React, { useRef, useState } from 'react'
import "../App.css"
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [showPassword, setShowPassword] = useState(false);
    const usernameRef = useRef();
    const passwordRef = useRef();
    const navigate = useNavigate();
    const handleCheckboxChange=()=>{
        setShowPassword(!showPassword);
    };
    const handleLoginSubmit = async(e)=>{
      e.preventDefault();
      try {
        const username = usernameRef.current.value;
        const password = passwordRef.current.value;
        const login = await fetch("http://localhost:8083/user/login", {
          method:"POST",
          headers:{
            "Content-Type":"Application/json"
          },
          body:JSON.stringify({username:username, password:password})
        });
        const userToken = await login.text();
        switch (userToken) {
          case "Invalid username or password":
            alert(`Invalid username or password...`);
            break;
          case "Failed to authenticate user":
            alert(`Internal Server Error...`);
            break;
          default:
            sessionStorage.setItem("token", JSON.stringify(userToken));
            sessionStorage.setItem("username", JSON.stringify(username));
            navigate("/quiz");
            break;
        }
      } catch (error) {
        console.log(error);
      }
    }
  return (
    <div className="loginComp">
      <div className="userForm">
        <h1>User Login</h1>
        <form onSubmit={handleLoginSubmit} method="post">
          <input type="text" placeholder="Username" ref={usernameRef} required={true} />
          <input type={showPassword ? "text" : "Password"} placeholder="Password" 
          ref={passwordRef} required={true}/>
          <div className="showpass">
              <label htmlFor="showPass">Show Password : </label>
              <input type="checkbox" name="showPass" class="show-password" 
              onChange={handleCheckboxChange} />
          </div>
          <button type="submit" className="submit-btn">Login</button>
          <p>Didn't have an account <a href="/register">Register here</a></p>
        </form>
      </div>
    </div>
  )
}

export default Login
