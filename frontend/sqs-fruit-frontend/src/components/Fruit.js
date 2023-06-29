import React, { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Button } from '@mui/material';

export default function Fruit() {
  const [name, setName] = useState('');
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);

  const handleClick = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/addFruit', {
  method: 'POST',
  headers: {
    'Accept': 'application/json, text/plain, */*',
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({ name })
})
  .then(res => {
    if (!res.ok) {
      throw new Error('Request failed with status ' + res.status);
    }
    return res.json();
  })
  .then(res => {
    // Check if the response indicates an error
    if (res.error) {
      // Handle the error response
      setError(res.error);
      setResponse(null);
    } else {
      // Process the successful response
      setResponse(res);
      setError(null);
    }
  })
  .catch(error => {
    // Handle network errors or other exceptions
    console.error(error);
    setError('Given Fruit does not exist in the database');
    setResponse(null);
  });
  }

  return (
    <Container>
      <h1>Search Fruit</h1>
      <Box
        component="form"
        sx={{
          '& > :not(style)': { m: 1 },
        }}
        noValidate
        autoComplete="off"
      >
        <TextField
          id="outlined-basic"
          label="Fruit Name"
          variant="outlined"
          fullWidth
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <Button id="Submitbutton" onClick={handleClick} variant="contained">
          SUBMIT
        </Button>
      </Box>

      {response && (
        <div>
          <h1>Response:</h1>
          <h2>{JSON.stringify(response, null, 2)}</h2>
        </div>
      )}
      {error && <p>{error}</p>}
    </Container>
  );
}

