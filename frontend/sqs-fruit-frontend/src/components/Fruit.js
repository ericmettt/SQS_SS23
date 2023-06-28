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
      .then(res => res.json())
      .then(res => {
        setResponse(res);
        setError(null);
      })
      .then(() =>
        fetch('http://localhost:8080/getFruit', {
          method: 'GET',
          headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
          },
        })
      )
      .then(res => res.json())
      .then(res => {
        if (res.length === 0) {
          setError('There is no such fruit');
        } else {
          console.log(res);
          // Further processing with the GET response if needed
        }
      })
      .catch(error => {
        setError("There is no such frut");
        setResponse(null);
      });
  };

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

