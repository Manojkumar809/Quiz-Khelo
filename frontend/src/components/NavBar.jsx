import React from 'react'
import { Link } from 'react-router-dom'
import "../App.css"

const NavBar = () => {
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