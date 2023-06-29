
import './App.css';
import Appbar from './components/Appbar';
import Fruit from './components/Fruit';
import { Helmet } from "react-helmet-async";

function App() {
  return (
    <div className="App">
      <Helmet>
  <meta
    http-equiv="Content-Security-Policy"
    content={`
      default-src 'self' http://localhost:*;
      script-src 'self';
      style-src 'self' 'unsafe-inline';
      img-src 'self' ;
      media-src 'self' ;
      frame-ancestors 'none';
    `}
  />
</Helmet>

      <Appbar></Appbar>
      <Fruit></Fruit>
    </div>
  );
}

export default App;
