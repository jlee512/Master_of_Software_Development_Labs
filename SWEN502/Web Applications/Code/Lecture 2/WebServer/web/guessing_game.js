/**
 * Created by julia on 25/10/2017.
 */

document.getElementById('restart').style.visibility = "hidden";
var answer = Math.ceil(Math.random() * 20);
var num_guesses = 5;
document.getElementById('guesses_remaining').innerHTML = "" + num_guesses;

function evaluate_guess(number) {
    if (number == answer) {
        document.getElementById('result').innerHTML = 'You Win!';
        document.getElementById('restart').style.visibility = "visible";
    } else if (number < answer) {
        document.getElementById('result').innerHTML = number + ' is too low';
        num_guesses--;
        document.getElementById('guesses_remaining').innerHTML = "" + num_guesses;
    } else if (number > answer) {
        document.getElementById('result').innerHTML = number + ' is too high';
        num_guesses--;
        document.getElementById('guesses_remaining').innerHTML = "" + num_guesses;
    }
}

document.getElementById('make_guess').addEventListener('click', function() {
    var number = document.getElementById('guess').value;
    evaluate_guess(number);
});

document.getElementById('restart').addEventListener('click', function() {
    location.reload();
});