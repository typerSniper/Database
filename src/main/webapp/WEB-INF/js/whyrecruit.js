window.onload = function() {

var chart1 = new CanvasJS.Chart("overallChart", {
    animationEnabled: true,
    title: {
        text: "Overall"
    },
    data: [{
        type: "pie",
        startAngle: 240,
        indexLabel: "{label} {y}",
        dataPoints: [
            {y: 150, label: "2 year M.Sc"},
            {y: 21, label: "5 year Integrated M.Sc"},
            {y: 510, label: "B.Tech"},
            {y: 270, label: "Dual Degree (B.Tech + M.Tech)"},
            {y: 52, label: "M.Des"},
            {y: 52, label: "M.Tech"},
            {y: 0, label: "M.Tech (IDP)"},
            {y: 120, label: "Ph.D"}
        ]
    }]
});
chart1.render();

var chart2 = new CanvasJS.Chart("btechChart", {
    animationEnabled: true,
    title: {
        text: "Btech."
    },
    data: [{
        type: "pie",
        startAngle: 240,
        indexLabel: "{label} {y}",
        dataPoints: [
            {y: 46, label: "Aerospace"},
            {y: 83, label: "Chemical"},
            {y: 87, label: "Civil"},
            {y: 90, label: "Computer Science"},
            {y: 54, label: "Electrical"},
            {y: 16, label: "Engineering Physics"},
            {y: 85, label: "Mechanical"},
            {y: 49, label: "Metallurgical"}
        ]
    }]
});
chart2.render();
}

// B.Tech
// Aerospace: 46
// Chemical: 83
// Civil: 87
// Computer Science: 90
// Electrical: 54
// Engineering Physics: 16
// Mechanical: 85
// Metallurgical: 49
// // Highcharts.com
// var chart1 = new CanvasJS.Chart("ddChart", {
//     animationEnabled: true,
//     title: {
//         text: "Overall"
//     },
//     data: [{
//         type: "pie",
//         startAngle: 240,
//         indexLabel: "{label} {y}",
//         dataPoints: [
//             {y: 150, label: "2 year M.Sc"},
//             {y: 21, label: "5 year Integrated M.Sc"},
//             {y: 510, label: "B.Tech"},
//             {y: 270, label: "Dual Degree (B.Tech + M.Tech)"},
//             {y: 52, label: "M.Des"},
//             {y: 52, label: "M.Tech"},
//             {y: 0, label: "M.Tech (IDP)"},
//             {y: 120, label: "Ph.D"},
//         ]
//     }]
// });
// chart1.render();

// }
// var chart1 = new CanvasJS.Chart("mtechChart", {
//     animationEnabled: true,
//     title: {
//         text: "Overall"
//     },
//     data: [{
//         type: "pie",
//         startAngle: 240,
//         indexLabel: "{label} {y}",
//         dataPoints: [
//             {y: 150, label: "2 year M.Sc"},
//             {y: 21, label: "5 year Integrated M.Sc"},
//             {y: 510, label: "B.Tech"},
//             {y: 270, label: "Dual Degree (B.Tech + M.Tech)"},
//             {y: 52, label: "M.Des"},
//             {y: 52, label: "M.Tech"},
//             {y: 0, label: "M.Tech (IDP)"},
//             {y: 120, label: "Ph.D"},
//         ]
//     }]
// });
// chart1.render();

// }
// var chart1 = new CanvasJS.Chart("mscChart", {
//     animationEnabled: true,
//     title: {
//         text: "Overall"
//     },
//     data: [{
//         type: "pie",
//         startAngle: 240,
//         indexLabel: "{label} {y}",
//         dataPoints: [
//             {y: 150, label: "2 year M.Sc"},
//             {y: 21, label: "5 year Integrated M.Sc"},
//             {y: 510, label: "B.Tech"},
//             {y: 270, label: "Dual Degree (B.Tech + M.Tech)"},
//             {y: 52, label: "M.Des"},
//             {y: 52, label: "M.Tech"},
//             {y: 0, label: "M.Tech (IDP)"},
//             {y: 120, label: "Ph.D"},
//         ]
//     }]
// });
// chart1.render();

// }



// Dual Degree
// Aerospace: 14
// Chemical: 43
// Civil: 6
// Electrical: 61
// Energy Science & Engineering: 28
// Engineering Physics: 12
// Mechanical: 50
// Metallurgical: 54
// Computer Science: 2
// Highcharts.com


// M.Tech
// Aerospace: 29
// Chemical: 16
// Civil: 46
// Computer Science: 103
// Earth Science: 13
// Electrical: 113
// Energy Science & Engineering: 18
// Mechanical: 61
// Metallurgical: 51
// Biomedical Engineering: 16
// Highcharts.com


// M.Sc
// Applied Geophysics: 10
// Applied Statistics & Informatics: 29
// Bioscience & Bioengineering: 11
// Chemistry: 26
// Earth Science: 32
// IEOR: 4
// Mathematics: 26
// Physics: 10
// CESE: 2
// Highcharts.com


// Interdisciplinary Programmes
// CESE: 13
// Geoinformatics & Resources Engineering: 19
// IEOR: 18
// Syscon: 9
// Technology & Development: 19
// Highcharts.com
// Home | Procedure | Why recruit | Academics | Student Profiles | Contact Us