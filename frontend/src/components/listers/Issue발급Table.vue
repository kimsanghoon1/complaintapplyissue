<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="values"
                :items-per-page="5"
                class="elevation-1"
        ></v-data-table>

        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                        v-model="openDialog"
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-fab-transition>
                            <v-btn
                                    color="primary"
                                    fab
                                    dark
                                    large
                                    style="position:absolute; bottom: 5%; right: 2%; z-index:99"
                                    @click="openDialog=true;"
                            >
                                <v-icon v-bind="attrs" v-on="on">mdi-plus</v-icon>
                            </v-btn>
                        </v-fab-transition>
                    </template>

                    <Issue발급 :offline="offline" class="video-card" :isNew="true" :editMode="true" v-model="newValue" @add="append" v-if="tick"/>
                
                    <v-btn
                            style="postition:absolute; top:2%; right:2%"
                            @click="closeDialog()"
                            depressed 
                            icon 
                            absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
    const axios = require('axios').default;
    import Issue발급 from './../Issue발급.vue';

    export default {
        name: 'Issue발급Manager',
        components: {
            Issue발급,
        },
        props: {
            offline: Boolean,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            values: [],
            headers: 
                [
                    { text: "발급파일일련번호", value: "발급파일일련번호" },
                    { text: "신청번호", value: "신청번호" },
                    { text: "파일Id", value: "파일Id" },
                    { text: "파일명", value: "파일명" },
                    { text: "파일경로명", value: "파일경로명" },
                    { text: "파일확장자명", value: "파일확장자명" },
                    { text: "파일크기", value: "파일크기" },
                    { text: "파일유형코드", value: "파일유형코드" },
                    { text: "파일순서", value: "파일순서" },
                    { text: "파일유형코드", value: "파일유형코드" },
                ],
            발급 : [],
            newValue: {},
            tick : true,
            openDialog : false,
        }),
        async created() {
            if(this.offline){
                if(!this.values) this.values = [];
                return;
            }

            var temp = await axios.get(axios.fixUrl('/발급s'))
            temp.data._embedded.발급s.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])
            this.values = temp.data._embedded.발급s;

            this.newValue = {
                '발급파일일련번호': 0,
                '신청번호': '',
                '파일Id': '',
                '파일명': '',
                '파일경로명': '',
                '파일확장자명': '',
                '파일크기': '',
                '파일유형코드': '',
                '파일순서': '',
                '파일유형코드': '',
            }
        },
        methods: {
            closeDialog(){
                this.openDialog = false
            },
            append(value){
                this.tick = false
                this.newValue = {}
                this.values.push(value)
                
                this.$emit('input', this.values);

                this.$nextTick(function(){
                    this.tick=true
                })
            },
        }
    }
</script>

