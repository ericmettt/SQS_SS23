import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container,Button } from '@mui/material';


export default function Fruit() {
    const[name,setName]=React.useState('');

    const handleClick=(e)=>{
      fetch('http://localhost:8080/addFruit', {
        method: 'POST',
        headers: {
          'Accept': 'application/json, text/plain, */*',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({name})
      }).then(res => res.json())
        .then(res => console.log(res))
        .then(res =>  fetch('http://localhost:8080/', {
          method: 'GET',
          headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
          },
        })).then(res => res.json())
        //.then(res => console.log(res.stringify))
        //.then(res => setName(res.json))
    }

    
  return (
    <Container>
       <h1>Search Fruit</h1>
            <Box
            component="form"
            sx={{
                '& > :not(style)': { m: 1},
            }}
            noValidate
            autoComplete="off"
            >
            <TextField id="outlined-basic" label="Fruit Name" variant="outlined" fullWidth
            value={name}
            onChange={(e)=>setName(e.target.value)} />
<Button id="Submitbutton" onClick={handleClick} variant="contained">SUBMIT 
</Button>

            </Box>
      
        
    </Container>    
  );
}
