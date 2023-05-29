function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var carModelApi = Vue.resource('/shop/models{/id}');

Vue.component('car-form', {
        props: ['cars', 'carAttr'],
        data: function () {
            return {
                id: '',
                labelName: '',
                modelName: '',
                price: ''
            }
        },
        watch: {
            carAttr: function (newVal, oldVal) {
                this.id = newVal.id;
                this.labelName = newVal.labelName;
                this.modelName = newVal.modelName;
                this.price = newVal.price;
            }
        },
        template:
            '<div>' +
            '<input type="text" placeholder="Enter car label" v-model="labelName" class="input-group mb-3"/>' +
            '<input type="text" placeholder="Enter car model" v-model="modelName" class="input-group mb-3"/>' +
            '<input type="number" placeholder="Enter car price" v-model="price" class="input-group mb-3"/>' +
            '<input class="btn btn-primary" type="button" value="Save" @click="save"/>' +
            '</div>',
        methods: {
            save: function () {
                var car = {labelName: this.labelName.trim(), modelName: this.modelName.trim(), price: this.price};
                if (this.id) {
                    carModelApi.update({id: this.id}, car).then(result =>
                        result.json().then(data => {
                            var index = getIndex(this.cars, data.id);
                            this.cars.splice(index, 1, data);
                            this.labelName = '';
                            this.modelName = '';
                            this.price = '';
                            }
                        ))
                } else {
                    carModelApi.save({}, car).then(result =>
                        result.json().then(data => {
                            this.cars.push(data);
                            this.labelName = '';
                            this.modelName = '';
                            this.price = '';
                        })
                    )
                }
            }
        }
    }
)

Vue.component('car-row-head', {
    props: ['car'],
    template:
        '<thead>' +
        '<tr>' +
        '<th scope="col">ID</th>' +
        '<th scope="col">Car Label</th>' +
        '<th scope="col">Car Model</th>' +
        '<th scope="col">Price</th>' +
        '</tr>' +
        '</thead>'
})

Vue.component('car-row', {
    props: ['car', 'editCar', 'cars'],
    template:
        '<tr class="carRow">' +
        '<th scope="col">{{ car.id }}</th>' +
        '<td>{{ car.labelName }}</td>' +
        '<td>{{ car.modelName }}</td>' +
        '<td>{{ car.price }}</td>' +
        '<span>' +
        '<input type="button" value="Edit" @click="edit" class="btn btn-success"/>' +
        '<input type="button" value="Delete" @click="del" class="btn btn-danger"/>' +
        '</span>' +
        '</tr>',
    methods: {
        edit: function () {
            this.editCar(this.car);
        },
        del: function () {
            carModelApi.remove({id: this.car.id}).then(result => {
                if (result.ok) {
                    this.cars.splice(this.cars.indexOf(this.car), 1)
                }
            })
        }
    }
})

Vue.component('car-list', {
    props: ['cars'],
    data: function () {
        return {
            car: null
        }
    },
    template:
        '<div>' +
        '<car-form :cars="cars" :carAttr="car"/>' +
        '<table class="table table-bordered my-2 table-hover">' +
        '<car-row-head/>' +
        '<tbody>' +
        '<car-row v-for="car in cars" :key="car.id" :car="car" :editCar="editCar" :cars="cars"/>' +
        '</tbody>' +
        '</table>' +
        '</div>',
    created: function () {
        carModelApi.get().then(result =>
            result.json().then(data =>
                data.forEach(car => this.cars.push(car))
            )
        )
    },
    methods: {
        editCar: function (car) {
            this.car = car;
        }
    }
});

var app = new Vue({
    el: '#app',
    template:
        '<car-list :cars="cars" />',
    data: {
        cars: []
    }
});

