import React from 'react'
import "../App.css"

const Home = () => {
  return (
    <div className="home">
      <div className="homeText">
        <h1>A good quiz is like a mental workout—stretching 
            the brain's muscles!
        </h1>
      </div>
      <div className="homeImage">
        <img src="static/questions.jpg" alt="questionsImg" />
      </div>
    </div>
  )
}

export default Home
