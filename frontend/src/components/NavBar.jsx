import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import "../App.css"

const NavBar = () => {
  const userToken = JSON.parse(sessionStorage.getItem("token"));
  const navigate = useNavigate();
  const handleLogout = (e) =>{
    e.preventDefault;
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("username");
    navigate("/login");
  }
  const toggleMenu = ()=>{
      const menu = document.getElementsByClassName("routes")[0];
      if(menu){
          menu.classList.toggle("active")
      }
  }
  return (
    <div>
      <nav className="navbar">
        <div className="logo">
            <Link to="/">Quiz Khelo</Link>
        </div>
        <ul className="routes">
            <li><Link to="/">home</Link></li>
            {userToken ? ( 
              <>
              <li><Link to="/login" onClick={handleLogout}>Logout</Link></li> 
              <li><Link to="/user" >Profile</Link></li> 
              </>
            ) : (
              <>
                <li><Link to="/login">login</Link></li>
                <li><Link to="/register">register</Link></li>
              </>
            )}
            <li><Link to="/quiz">quiz</Link></li>
            <li><Link to="/about">about</Link></li>
        </ul>
        <div className="menu" onClick={toggleMenu}>
            <div className="bar"></div>
            <div className="bar"></div>
            <div className="bar"></div>
        </div>
      </nav>
    </div>
  )
}

export default NavBar