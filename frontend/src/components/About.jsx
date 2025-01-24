import React from 'react'
import "../App.css"

const About = () => {
  return (
    <div className="aboutComp">
        <header>
            <h1>About Quiz Khelo</h1>
        </header>

        <section>
            <h2>Welcome to Quiz Khelo!</h2>
            <p>This app allows users to create personalized quizzes, solve them, and get instant feedback on their performance. Whether you're looking to test your knowledge, prepare for exams, or just have some fun, Quiz Khelo is designed to provide an engaging and interactive quiz experience.</p>
        </section>

        <section>
            <h2>How It Works</h2>
            <ul>
                <li><strong>Create Your Quiz: </strong>Simply enter the title, topic, and the number of questions you want to answer. Based on your inputs, a custom quiz will be generated for you.</li>
                <li><strong>Solve the Questions: </strong>Answer each question and get an instant score after completing the quiz.</li>
                <li><strong>Instant Results: </strong>After submitting the quiz, your score is calculated and displayed in real-time, giving you immediate feedback on your performance.</li>
            </ul>
        </section>

        <section>
            <h2>Key Features</h2>
            <ul>
                <li><strong>Customizable Quizzes:</strong> Tailor your quiz by selecting the title, topic, and number of questions.</li>
                <li><strong>Real-Time Scoring:</strong> Get instant feedback after completing the quiz.</li>
                <li><strong>User-Friendly Interface:</strong> A simple, responsive, and intuitive design for a smooth experience.</li>
                <li><strong>Mobile and Desktop Compatibility:</strong> The app is fully responsive and works seamlessly across devices.</li>
            </ul>
        </section>

        <section>
            <h2>Technologies Used</h2>
            <ul>
                <li><strong>Backend:</strong> The backend of the app is built using Java Spring Boot in a microservices architecture. Each service is independently responsible for a specific aspect of the application, such as quiz creation, question retrieval, and score calculation.</li>
                <li><strong>Frontend:</strong> The frontend is built using React.js for a dynamic and interactive user experience. The app uses state management (with React hooks) to keep track of the quiz state in real-time.</li>
                <li><strong>Database:</strong> The app uses MySQL to store quiz-related data. MySQL ensures reliable and efficient storage for user-created quizzes, questions, answers, and results.</li>
                <li><strong>Microservices Architecture:</strong> The app follows a microservices architecture, where different services communicate with each other through well-defined APIs. This approach allows for better scalability, easier maintenance, and the ability to add more features as the app grows.</li>
                <li><strong>API Gateway:</strong> Spring Cloud API Gateway is used for routing between microservices. It enhances communication between the frontend and backend, ensuring efficient data flow and handling cross-service interactions.</li>
            </ul>
        </section>

        <section>
            <h2>Why I Built This App</h2>
            <p>This project was an excellent opportunity to apply the principles of microservices architecture and full-stack development. By combining Spring Boot for the backend, React.js for the frontend, and MySQL for data storage, I was able to create a robust and scalable quiz app.</p>
            <ul>
                <li>Implement RESTful APIs to manage quiz creation, user progress, and score calculation.</li>
                <li>Explore microservices for building loosely coupled and independently deployable services.</li>
                <li>Use React hooks for managing state and improving the user experience.</li>
                <li>Handle CORS issues between the frontend and backend for seamless communication.</li>
                <li>Gain hands-on experience working with MySQL and integrating it with Java Spring Boot.</li>
            </ul>
        </section>

        <section>
            <h2>Future Enhancements</h2>
            <ul>
                <li><strong>User Authentication:</strong> Implementing login and registration functionality to allow users to save their quizzes and scores.</li>
                <li><strong>Advanced Question Types:</strong> Expanding to include different types of questions, such as multiple choice, fill-in-the-blank, and more.</li>
                <li><strong>Analytics:</strong> Providing users with detailed analytics on their quiz performance over time.</li>
            </ul>
        </section>

        <footer>
            <p>&copy; 2025 Quiz Khelo. All Rights Reserved.</p>
        </footer>
    </div>
  )
}

export default About
