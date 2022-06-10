import './App.css';

import { Button, Empty } from "antd";

import { getAllStudents } from "./client";

function App() {
    getAllStudents().then(res => res.json())
        .then(console.log);
  return (
    <div className="App">
      <h1>Homo</h1>
        <Empty>
            <Button type='primary'>Press me!</Button>
        </Empty>
    </div>
  );
}

export default App;
