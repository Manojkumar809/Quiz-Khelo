import './App.css'
import Home from './components/Home'
import NavBar from './components/NavBar.Jsx'
import { Routes, Route } from 'react-router-dom'
import Quiz from './components/Quiz'
import PlayQuiz from './components/PlayQuiz'
import About from './components/About'
import Login from './components/Login'
import Register from './components/Register'
import UserProfile from './components/UserProfile'

function App() {
  return (
    <>
        <NavBar />
        <Routes>
          <Route path="/" element={<Home />}/>
          <Route path="/login" element={<Login />}/>
          <Route path="/register" element={<Register />}/>
          <Route path="/playQuiz" element={<PlayQuiz />}/>
          <Route path="/user" element={<UserProfile />}/>
          <Route path="/quiz" element={<Quiz />}/>
          <Route path="/about" element={<About />}/>
        </Routes>
    </>
  )
}

export default App
