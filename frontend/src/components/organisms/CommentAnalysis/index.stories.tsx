import { ComponentMeta, ComponentStory } from '@storybook/react';
import CommentAnalysis from '@/components/organisms/CommentAnalysis';

export default {
  title: 'organisms/CommentAnalysis',
  component: CommentAnalysis,
} as ComponentMeta<typeof CommentAnalysis>;

const Template: ComponentStory<typeof CommentAnalysis> = () => <CommentAnalysis />;

export const Primary = Template.bind({});
Primary.args = {};
