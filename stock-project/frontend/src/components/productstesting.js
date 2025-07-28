import axios from "axios";


const fetchdata=async(url)=>{

    const response = await axios.get(url,
        { withCredentials: true }
    )
    .then((response)=>{
        
        return response.data
    })
    .catch((err)=>{
        console.log(err)
    })
    return (response)
 
}

export default fetchdata;