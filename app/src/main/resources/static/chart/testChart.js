var chartData = {
                    // ①차트의 종류(String)
                    type: 'bar',
                    // ②차트의 데이터(Object)
                    data: {
                        // ③x축에 들어갈 이름들(Array)
                        labels: [],
                        // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
                        datasets: [{
                            // ⑤dataset의 이름(String)
                            label: '점수',
                            // ⑥dataset값(Array)
                            data: [],
                            // ⑦dataset의 배경색(rgba값을 String으로 표현)
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            // ⑧dataset의 선 색(rgba값을 String으로 표현)
                            borderColor: 'rgba(255, 99, 132, 1)',
                            // ⑨dataset의 선 두께(Number)
                            borderWidth: 1
                        }]
                    },
                    // ⑩차트의 설정(Object)
                    options: {
                        responsive: false,
                        // ⑪축에 관한 설정(Object)
                        scales: {
                            // ⑫y축에 대한 설정(Object)
                            y: {
                                // ⑬시작을 0부터 하게끔 설정(최소값이 0보다 크더라도)(boolean)
                                beginAtZero: true
                            }
                        }
                    }
                }
function loadData(){
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8081/student_score";
    var currentURL = window.location.href;
    console.log('test');
    url += '?'+currentURL.split('?')[1].split('&')[0] + '&' + currentURL.split('?')[1].split('&')[1];
    console.log(url);
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function(){
        var scores = [];
        var labels = [];

        if(xhr.status===200 || xhr.status === 201){
            var responseData = JSON.parse(xhr.responseText);
            responseData.forEach(function(item){
                scores.push(item['score']);
                labels.push(item['subject']);
            })
            console.log(scores);
            console.log(labels);
            chartData.data.labels = labels;
            chartData.data.datasets[0]['data'] = scores;

            // 해당 부분은 JS파일을 따로 만들어서 사용해도 된다.
            // 차트를 그럴 영역을 dom요소로 가져온다.
            var chartArea = document.getElementById('myChart').getContext('2d');
            // 차트를 생성한다.
            var myChart = new Chart(chartArea, chartData);
        }
        else
            console.error(xhr.statusText);
    }
    xhr.send();
}
window.onload = loadData();

