import React, { useState, useEffect } from 'react';
import "./ChatBox.css"

const ChatBox = () => {
  const [inputMessage, setInputMessage] = useState(''); // Estado para el mensaje de entrada
  const [apiResponse, setApiResponse] = useState(null); // Estado para la respuesta de la API
  const [errorMessage, setErrorMessage] = useState(null); // Estado para manejar el error personalizado
  const [debouncedInput, setDebouncedInput] = useState(inputMessage); // Estado para manejar el input con debounce

  // useEffect para manejar el debounce del inputMessage
  useEffect(() => {
    const handler = setTimeout(() => {
      setDebouncedInput(inputMessage); // Actualizar el input después del tiempo de espera
    }, 500); // Esperar 500ms

    // Limpiar el timeout si el inputMessage cambia antes de que se complete el timeout
    return () => {
      clearTimeout(handler);
    };
  }, [inputMessage]); // Ejecutar cada vez que el inputMessage cambie

  // useEffect para hacer la petición a la API cuando el input con debounce cambie
  useEffect(() => {
    const fetchData = async () => {
      if (debouncedInput.trim()) {
        try {
          const response = await fetch(`http://localhost:1212/chat`);
          const data = await response.json();
          setApiResponse(data); // Guardar la respuesta en el estado
        } catch (error) {
          console.error('Error fetching data:', error);
          setErrorMessage();
        }
      }
    };

    fetchData();
  }, [debouncedInput]); // Ejecutar el efecto cuando el input con debounce cambie
  return (
    <div className="chat-box">
      <div className="chat-header">
        <h3>Chat Box</h3>
      </div>
      <div className="chat-messages">
        {/* Mostrar la respuesta de la API */}
        {apiResponse && (
          <div className="api-response">
            <h4>Resultados de la API:</h4>
            <pre>{JSON.stringify(apiResponse, null, 2)}</pre>
          </div>
        )}
        
        {/* Mostrar mensaje de error personalizado */}
        {errorMessage && (
          <div className="error-message">
            <h4>Error:</h4>
            <p>{errorMessage}</p>
          </div>
        )}
      </div>
      <form className="chat-input" onSubmit={(e) => e.preventDefault()}>
        <input
          type="text"
          value={inputMessage}
          onChange={(e) => setInputMessage(e.target.value)} // Actualizar inputMessage cuando se escribe algo
          placeholder="Escribe un mensaje..."
        />
      </form>
    </div>
  );
};

export default ChatBox;
