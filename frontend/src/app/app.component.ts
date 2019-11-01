import {Component, OnInit} from '@angular/core';
import {GreeterClient} from "./grpc/service_grpc_web_pb";
import {HelloReply, HelloRequest} from "./grpc/service_pb";
import * as grpcWeb from 'grpc-web';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {
  serverResponse: string = "";
  serverError: grpcWeb.Error = null;
  ngOnInit(): void {
    const greeterService = new GreeterClient("http://localhost:8080");

    const request = new HelloRequest();
    request.setName("Toto");
    greeterService.sayHello(request, {}, (err: grpcWeb.Error, response: HelloReply) => {
      if (err) {
        this.serverError = err;
        return;
      }
      this.serverResponse = response.getMessage();
    });
  }
}
