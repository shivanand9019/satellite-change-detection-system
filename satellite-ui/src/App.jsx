import { useState } from "react";
import axios from "axios";

function App() {
  const [fieldId, setFieldId] = useState("");
  const [date1, setDate1] = useState("");
  const [date2, setDate2] = useState("");
  const [message, setMessage] = useState("");

  const submitRequest = async () => {
    try {
      await axios.post("http://localhost:8085/ingest/trigger", {
        fieldId: Number(fieldId),
        date1,
        date2,
      });

      setMessage("Analysis Request Submitted Successfully");
    } catch (err) {
      setMessage("Failed to submit request");
      console.error(err);
    }
  };

  return (
    <div
      style={{
        width: "400px",
        margin: "50px auto",
        padding: "20px",
        border: "1px solid #ddd",
        borderRadius: "10px",
      }}
    >
      <h2>Satellite Change Detection</h2>

      <input
        placeholder="Field ID"
        value={fieldId}
        onChange={(e) => setFieldId(e.target.value)}
        style={{ width: "100%", padding: "10px", marginBottom: "10px" }}
      />

      <input
        type="date"
        value={date1}
        onChange={(e) => setDate1(e.target.value)}
        style={{ width: "100%", padding: "10px", marginBottom: "10px" }}
      />

      <input
        type="date"
        value={date2}
        onChange={(e) => setDate2(e.target.value)}
        style={{ width: "100%", padding: "10px", marginBottom: "10px" }}
      />

      <button
        onClick={submitRequest}
        style={{
          width: "100%",
          padding: "10px",
          cursor: "pointer",
        }}
      >
        Start Analysis
      </button>

      {message && (
        <p style={{ marginTop: "15px" }}>
          {message}
        </p>
      )}
    </div>
  );
}

export default App;