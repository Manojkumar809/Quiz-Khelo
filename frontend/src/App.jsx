import './App.css'
import Home from './components/Home'
import NavBar from './components/NavBar.Jsx'
import { Routes, Route } from 'react-router-dom'
import Quiz from './components/Quiz'
import PlayQuiz from './components/PlayQuiz'
import About from './components/About'

function App() {
  return (
    <>
      <NavBar />
      <Routes>
        <Route path="/" element={<Home />}/>
        <Route path="/quiz" element={<Quiz />}/>
        <Route path="/about" element={<About />}/>
        <Route path="/playQuiz" element={<PlayQuiz />}/>
      </Routes>
    </>
  )
}

export default App
