	function calc(){
		var first = document.getElementById('first').value;
		var second = document.getElementById('second').value;

		if(first != '' && second != ''){
			var sum = parseInt(first) + parseInt(second);

			document.getElementById('info').innerText = 'Результат: '+sum;
		}
	}
