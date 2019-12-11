import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
@Component({
  selector: 'jhi-revenue-second-line-chart',
  templateUrl: './revenue-second-line-chart.component.html',
  styleUrls: ['./revenue-second-line-chart.component.scss']
})
export class RevenueSecondLineChartComponent implements OnInit {
  Linechart = [];
  constructor() {}

  ngOnInit() {
    this.Linechart = new Chart('revenueSecondLineChart', {
      type: 'line',
      data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
          {
            data: [100, 200, 300, 75, 77, 75],
            label: 'Crude oil prices',
            borderColor: 'green',
            backgroundColor: 'red'
          }
        ]
      },
      options: {
        title: {
          display: true,
          text: 'Bảng doanh thu theo tháng',
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
  }
}
