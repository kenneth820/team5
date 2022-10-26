/**
 * 
 */
function change3()
{
  document.addEventListener('click', function(event) {
    let id = event.target.dataset.toggleId1;
    if (!id) return;

    let elem = document.getElementById(id);

    elem.hidden = !elem.hidden;


  });

}
function change4()
{
  document.addEventListener('click', function(event) {
    let id = event.target.dataset.toggleId2;   
    if (!id) return;

    let elem = document.getElementById(id);

    elem.hidden = !elem.hidden;
    
    
  });

}
function change5()
{
  document.addEventListener('click', function(event) {
    let id = event.target.dataset.toggleId3;
    if (!id) return;

    let elem = document.getElementById(id);

    elem.hidden = !elem.hidden;
  });

}






