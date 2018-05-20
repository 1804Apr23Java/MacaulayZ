import { Injectable } from '@angular/core';
import {Http, Response} from '@angular/http';
import {Movie} from './movie.model';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable()

export class MovietitleapiService {

  constructor(private http: Http) { }

  public fetchMovieInformation(title: string): Observable<Movie> {
    title = title.replace(" ", "+");
    return this.http.get(`http://www.omdbapi.com/?t=${title}&apikey=5ae0fcd1`).pipe(map((response: Response) => {
      return <Movie> response.json();
    })); 
  }

  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
}
