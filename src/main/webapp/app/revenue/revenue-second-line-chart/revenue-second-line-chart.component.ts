import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { ResponseDTO } from 'app/revenue/revenue-line-chart/revenue-line-chart.component';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'jhi-revenue-second-line-chart',
  templateUrl: './revenue-second-line-chart.component.html',
  styleUrls: ['./revenue-second-line-chart.component.scss']
})
export class RevenueSecondLineChartComponent implements OnInit {
  Linechart = [];
  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<ResponseDTO>('http://localhost:8080/profit/m').subscribe(response => {
      this.Linechart = new Chart('revenueSecondLineChart', {
        type: 'line',
        data: {
          labels: response.dates,
          datasets: [
            {
              data: response.profit.map(p => p.totalProfit),
              label: 'Doanh thu',
              borderColor: 'green',
              backgroundColor: 'red'
            }
          ]
        },
        options: {
          title: {
            display: true,
            text: 'Bảng doanh thu 30 ngày gần nhất',
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
