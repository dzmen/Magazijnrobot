int getValue(String data, char separator, int index)
{
    int maxIndex = data.length()-1;
    int j=0;
    String chunkVal = "";

    for(int i=0; i<=maxIndex && j<=index; i++)
    {
      chunkVal.concat(data[i]);
      if(data[i]==separator)
      {
        j++;
        if(j>index)
        {
          chunkVal.trim();
          return chunkVal.toInt();
        }    
        chunkVal = "";    
      }  
    }  
}

int berekenX(int nx){
  
}

int berekenY(int ny){
  
}
