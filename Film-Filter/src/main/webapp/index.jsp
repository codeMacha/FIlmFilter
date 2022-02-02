<html>
<head>
<meta charset="UTF-8">
<title>Film Filter</title>
<!-- bootstrap style sheet link -->
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <!-- this jumbotron is copied from the bootstrap website -->
        <div class="jumbotron jumbotron-fluid">
            <div class="container p-3 my-3 bg-dark text-white">
                <h1 class="display-4">Movie Filter</h1>
                <h6>Request a list of movies to pick from. Fill out our form below </h6>
            </div>
        </div>
        
        <!-- action should be the name of our servlet class -->
        <form action="FilmServlet" method="post">
        		<p>Ratings</p>
        		<input type="radio" id="G" name="rating" value="G">
        		<label for="G">G</label><br>
        		<input type="radio" id="PG" name="rating" value="PG">
        		<label for="PG">PG</label><br>
        		<input type="radio" id="PG_13" name="rating" value="PG-13">
        		<label for="PG-13">PG-13</label><br>
        		<input type="radio" id="NC-17" name="rating" value="NC-17">
        		<label for="NC-17">NC-17</label><br>
        		<input type="radio" id="R" name="rating" value="R">
        		<label for="R">R</label><br>
        		
        		<br>
        		
        		<p>Rental Rate</p>
        		<input type="radio" id="0.99" name="rental_rate" value="0.99">
        		<label for="G">0.99</label><br>
        		<input type="radio" id="2.99" name="rental_rate" value="2.99">
        		<label for="PG">2.99</label><br>
        		<input type="radio" id="4.99" name="rental_rate" value="4.99">
        		<label for="PG-13">4.99</label><br>
        		
                <label for="result-size">Result Size</label>
                <input type="number" id="result-size" name="result-size" min="1" max="20"><br><br>
 
            
            <input type="submit" value="Search" class="btn btn-primary">
            <input type="reset" value="clear">
        
        </form>
    </div>
</body>
</html>