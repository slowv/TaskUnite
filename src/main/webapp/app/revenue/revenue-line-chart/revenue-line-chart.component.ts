import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'jhi-revenue-line-chart',
  templateUrl: './revenue-line-chart.component.html',
  styleUrls: ['./revenue-line-chart.component.scss']
})
export class RevenueLineChartComponent implements OnInit {
  Linechart = [];
  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<ResponseDTO>('http://localhost:8080/profit/w').subscribe(response => {
      this.Linechart = new Chart('revenueLineChart', {
        type: 'line',
        data: {
          labels: response.dates,
          datasets: [
            {
              data: response.profit.map(p => p.totalProfit),
              label: 'Doanh thu',
              borderColor: '#3cb371',
              backgroundColor: '#0000FF'
            }
          ]
        },
        options: {
          title: {
            display: true,
            text: 'Bảng doanh thu 7 ngày gần nhất',
            position: 'bottom'
          },
          legend: {
            display: false
          },
          responsive: true,
          maintainAspectRatio: false,
          scales: {
            xAxes: [
              {
                display: true
              }
            ],
            yAxes: [
              {
                display: true
              }
            ]
          }
        }
      });
    });
  }
}

export class ResponseDTO {
  dates: [];
  profit: ProfitDto[];
}

export class ProfitDto {
  totalProfit: number;
}
