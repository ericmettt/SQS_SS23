import React from 'react';
import { render, screen } from '@testing-library/react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Button } from '@mui/material';

class FruitSearchTest extends React.Component {
  render() {
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
          />
          <Button id="Submitbutton" variant="contained">
            SUBMIT
          </Button>
        </Box>

      
      </Container>
    );
  }
}

describe('FruitSearchTest', () => {
  it('renders the heading, textfield, and submit button', () => {
    render(<FruitSearchTest />);
    const headingElement = screen.getByText('Search Fruit');
    const textFieldElement = screen.getByLabelText('Fruit Name');
    const submitButtonElement = screen.getByRole('button', { name: 'SUBMIT' });

    expect(headingElement).toBeInTheDocument();
    expect(textFieldElement).toBeInTheDocument();
    expect(submitButtonElement).toBeInTheDocument();
  });
});
