import React, { useState } from 'react';
import { validate } from "uuid";

import SearchBar from '../Components/SearchBar';

import Header from '../Components/Header';

export default function HomePage() {
  const [data, setData] = useState();

  const handleSearch = async (query) => {
    let fetchData;
    try{
      if(validate(query)){ // is UUID
        const res = await fetch(`http://localhost:4000/api/clients/${query}`);
        if (!res.ok) 
          throw new Error('Failed to fetch by UUID');
        
        fetchData = await res.json();
      
      }else{ //is name
        const res = await fetch(`http://localhost:4000/api/clients?name=${query}`);
        if (!res.ok) 
          throw new Error('Failed to fetch by UUID');
        
        fetchData = await res.json();
      }

      setData(fetchData);
      console.log(data);
    }catch(err){
      console.error('Error fetching data:', err);
      setData(null);
    }
  }

  return (
    <div>
      <div>
        <SearchBar onSearch={handleSearch} />
      </div>
      
    </div>
  );
}