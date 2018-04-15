var App = {};

App.overlay = new Vue({
    el: "#overlay",
    data: {},
    methods: {
        hide: function() {
            $(this.$el).hide();
        },
        show: function() {
            $(this.$el).show();
        }
    }
});

App.navbar = new Vue({
    el: "#navbar",
    data: {},
    methods: {
        logout: function() {
            $.post("logout")
                .then(function() {
                    window.location.href = "";
                });
        }
    }
});

App.header = new Vue({
    el: "#header",
    data: {},
    methods: {
        
    }
});

App.projects = new Vue({
    el: "#projects",
    data: {
        my: [],
        all: []
    },
    methods: {
        refresh: function() {
            App.overlay.show();
            $.get("projects")
                .then(function (data) {
                    this.my = data.my;
                    this.all = data.all;
                })
                .always(function() {
                    App.overlay.hide();
                })
        }
    }
});
