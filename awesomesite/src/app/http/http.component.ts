import { Component, OnInit } from '@angular/core';
import { MovietitleapiService } from '../movietitleapi.service';
import { Movie } from '../movie.model';

@Component({
  selector: 'app-http',
  templateUrl: './http.component.html',
  styleUrls: ['./http.component.css']
})
export class HttpComponent implements OnInit {

  constructor(private movieService: MovietitleapiService) { }
  
  public movie: Movie = new Movie('Snatch', 0, '');

  getMovieInformation(): void {
    this.movieService.fetchMovieInformation(this.movie.title).subscribe(
      movie => this.movie = movie,
      error => console.log(`Error: ${error}`)
    );
  }

  ngOnInit() {
    this.getMovieInformation();
  }

}
