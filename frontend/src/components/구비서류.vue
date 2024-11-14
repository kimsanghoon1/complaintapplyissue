<template>

    <v-card outlined>
        <v-card-title>
            구비서류
        </v-card-title>

        <v-card-text>
            <String label="파일id" v-model="value.파일id" :editMode="editMode"/>
            <String label="파일명" v-model="value.파일명" :editMode="editMode"/>
            <String label="파일경로명" v-model="value.파일경로명" :editMode="editMode"/>
            <String label="파일확장자명" v-model="value.파일확장자명" :editMode="editMode"/>
            <String label="파일크기" v-model="value.파일크기" :editMode="editMode"/>
            <파일유형코드 offline label="파일유형코드" v-model="value.파일유형코드" :editMode="editMode" @change="change"/>
            <String label="파일순서" v-model="value.파일순서" :editMode="editMode"/>
            <Boolean label="사용여부" v-model="value.사용여부" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </v-card>
</template>

<script>

    export default {
        name: '구비서류',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
        }),
        async created() {
            if(!Object.values(this.value)[0]) {
                this.$emit('input', {});
                this.newValue = {
                    '파일id': '',
                    '파일명': '',
                    '파일경로명': '',
                    '파일확장자명': '',
                    '파일크기': '',
                    '파일유형코드': '',
                    '파일순서': '',
                    '사용여부': '',
                }
            }
            if(typeof this.value === 'object') {
                if(!('파일id' in this.value)) {
                    this.value.파일id = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('파일명' in this.value)) {
                    this.value.파일명 = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('파일경로명' in this.value)) {
                    this.value.파일경로명 = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('파일확장자명' in this.value)) {
                    this.value.파일확장자명 = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('파일크기' in this.value)) {
                    this.value.파일크기 = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('파일순서' in this.value)) {
                    this.value.파일순서 = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('사용여부' in this.value)) {
                    this.value.사용여부 = false;
                }
            }
        },
        watch: {
            value(val) {
                this.$emit('input', val);
            },
            newValue(val) {
                this.$emit('input', val);
            },
        },

        methods: {
            edit() {
                this.editMode = true;
            },
            async add() {
                this.editMode = false;
                this.$emit('input', this.value);

                if(this.isNew){
                    this.$emit('add', this.value);
                } else {
                    this.$emit('edit', this.value);
                }
            },
            async remove(){
                this.editMode = false;
                this.isDeleted = true;

                this.$emit('input', this.value);
                this.$emit('delete', this.value);
            },
            change(){
                this.$emit('change', this.value);
            },
        }
    }
</script>

