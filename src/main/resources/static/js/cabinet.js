var userApi = Vue.resource('/shop/user');

// Vue.component('user', {
//         // path: '/shop/user',
//         props: ['user'],
//         data: function () {
//             return {
//                 id: '',
//                 login: ''
//             }
//         },
//         template:
//             '<main class="form-signin w-100 m-auto">' +
//             '<h1 class="h3 mb-3 fw-normal">Please, enter</h1>' +
//             '<div class="form-floating">' +
//             '<h3 type="text" class="form-control" id="floatingPassword">{{ user.id }}</h3>' +
//             '<label htmlFor="floatingInput">ID</label>' +
//             '</div>' +
//             '<div class="form-floating">' +
//             '<h3 type="text" class="form-control" id="floatingPassword">{{ user.login }}</h3>' +
//             '<label htmlFor="floatingPassword">Login</label>' +
//             '</div>' +
//             '</main>',
//         created: function () {
//             console.log('ggggg')
//             // debugger
//             // userApi.get().then(result =>
//             //     result.json().then(function () {
//             //        console.log(result.text())
//             //     })
//             // )
//         },
//     }
// )
//
// var app = new Vue({
//     el: '#user',
//     template:
//         '<user :user="user"/>',
//     data: {
//         user: 'user'
//     }
// });