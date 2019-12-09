import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
@Component({
  selector: 'jhi-revenue-line-chart',
  templateUrl: './revenue-line-chart.component.html',
  styleUrls: ['./revenue-line-chart.component.scss']
})
export class RevenueLineChartComponent implements OnInit {
  Linechart = [];
  constructor() {}

  ngOnInit() {
    this.Linechart = new Chart('revenueLineChart', {
      type: 'line',
      data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
          {
            data: [85, 72, 78, 75, 77, 75],
            label: 'Crude oil prices',
            borderColor: '#3cb371',
            backgroundColor: '#0000FF'
          }
        ]
      },
      options: {
        legend: {
          display: false
        },
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
