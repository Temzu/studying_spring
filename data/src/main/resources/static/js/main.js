let productApi = Vue.resource('/app/products{/id}');

Vue.component('product-row', {
    props: ['product'],
    template: '<div>{{ product.id }} {{ product.title }} {{ product.price }}</div>'
});

Vue.component('products-list', {
    props: ['products'],
    template:
        '<div>' +
            '<product-row v-for="product in products" :key="product.id" :product="product"/>' +
        '</div>'
});

let prod = new Vue({
    el: '#prod',
    template: '<products-list :products="products" />',
    data: {
        products: []
    },
    created: function () {
        productApi.get().then(result => {
            result.json().then(data =>
                data.forEach(product => this.products.push(product))
            )
        })
    }
});