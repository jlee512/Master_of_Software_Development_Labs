/**
 * Created by julia on 25/10/2017.
 */
document.getElementById('getResourceButton').addEventListener('click', async function() {
       var response = await fetch('resource.txt');
       var text = await response.text();
       document.getElementById('content').innerHTML = text;
    });