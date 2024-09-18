import { useState } from "react"

export default function SearchBar({onSearch}){
    const [query, setQuery] = useState('');

    const handleInputChange = (e) => {
        setQuery(e.target.value)
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        await onSearch(query); 
      };

    return(
        <div>            
            <form onSubmit={handleSubmit}>
                <div>
                    <input type="text" value={query} onChange={handleInputChange}
                        placeholder='Search Client Name or By Id'/>
                </div>

                <div>
                    <button type='submit'>Search</button>
                </div>
            </form>
        </div>
    )
}