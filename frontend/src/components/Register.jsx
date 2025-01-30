import React, { useRef, useState } from 'react'
import "../App.css"
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const [showPassword, setShowPassword] = useState(false);
    const navigate = useNavigate();
    const usernameRef = useRef();
    const passwordRef = useRef();
    const confirmPasswordRef = useRef();
    const handleCheckboxChange=()=>{
        setShowPassword(!showPassword);
    };
    const handleRegisterSubmit = async(e)=>{
      e.preventDefault();
      const username = usernameRef.current.value;
      const password = passwordRef.current.value;
      const confirmPassword = confirmPasswordRef.current.value;
      if(password === confirmPassword){
        const register = await fetch("http://localhost:8083/user/register", {
          method:"POST",
          headers:{
            "Content-Type":"Application/json"
          },
          body:JSON.stringify({username, password})
        });
        const registerRes = await register.text();
        if(registerRes === "User Successfully Created"){
          alert(`Account creation successfull now login to continue`);
          navigate("/login");
        }else{
          alert('Account creation failed');
        }
      }else{
        alert(`Enter the credentials carefully`);
      }
    }
  return (
    <div className="registerComp">
      <div className="userForm">
        <h1>User Registration</h1>
        <form action="" method="post" onSubmit={handleRegisterSubmit} >
        <input type="text" placeholder="Username" ref={usernameRef} required={true}/>
            <input type={showPassword ? "text" : "password"} placeholder="Password" 
            required={true} ref={passwordRef}/>
            <input type={showPassword ? "text" : "password"} placeholder="Confirm Password" 
            required={true} ref={confirmPasswordRef}/>
            <div className="showpass">
                <label htmlFor="showPass">Show Password : </label>
                <input type="checkbox" name="showPass" class="show-password" onChange={handleCheckboxChange} />
            </div>
            <button type="submit" className="submit-btn" >Register</button>
            <p>Already have an account <a href="/login">Login here</a></p>
        </form>
      </div>
    </div>
  )
}

export default Register