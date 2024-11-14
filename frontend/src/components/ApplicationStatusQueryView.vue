<template>

    <v-data-table
        :headers="headers"
        :items="applicationStatusQuery"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ApplicationStatusQueryView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "status", value: "status" },
            ],
            applicationStatusQuery : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/applicationStatusQueries'))

            temp.data._embedded.applicationStatusQueries.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.applicationStatusQuery = temp.data._embedded.applicationStatusQueries;
        },
        methods: {
        }
    }
</script>

